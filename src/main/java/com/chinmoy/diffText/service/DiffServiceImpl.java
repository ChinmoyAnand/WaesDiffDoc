package com.chinmoy.diffText.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinmoy.diffText.entity.DiffDocument;
import com.chinmoy.diffText.exception.DocNotExistException;
import com.chinmoy.diffText.exception.InvalidFileException;
import com.chinmoy.diffText.exception.SideIsEmptyException;
import com.chinmoy.diffText.model.DiffCriteria;
import com.chinmoy.diffText.model.DiffResult;
import com.chinmoy.diffText.model.ResponseDTO;
import com.chinmoy.diffText.model.Side;
import com.chinmoy.diffText.repository.DiffRepository;

@Service
public class DiffServiceImpl implements DiffService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DiffService.class);
	
	@Autowired
    private DiffRepository diffRepo;

	@Override
	public ResponseDTO getDiffById(String diffid, DiffCriteria criteria) throws DocNotExistException{
		LOG.info("Getting the ID {} ", diffid);
		Optional<DiffDocument> docLeft = diffRepo.findByDiffidAndSide(diffid,Side.LEFT);
		Optional<DiffDocument> docRight = diffRepo.findByDiffidAndSide(diffid,Side.RIGHT);
		if (!docLeft.isPresent() && !docRight.isPresent()) {
            throw new DocNotExistException();
        }
		return checkDiff(docLeft.get(),docRight.get(),criteria);
	}

	private ResponseDTO checkDiff(DiffDocument docLeft, DiffDocument docRight, DiffCriteria criteria) {
		final byte[] left = docLeft.getDoc().getData();
		final byte[] right = docRight.getDoc().getData();
		final List<String> offsets = new ArrayList<>();
		int length = 0;
        int offset = -1;
		
		if (Arrays.equals(left, right)) {
            return ResponseDTO.builder().result(DiffResult.EQUALS).build();
        }
		if (left.length != right.length) {
            return ResponseDTO.builder().result(DiffResult.NOT_EQUAL_SIZE).build();
        }
		if(criteria == null || criteria.equals(DiffCriteria.DEFAULT)) {
			LOG.info("Same size Doc but content are different, Finding Diffin based on Encoded binary array");
	        for (int i = 0; i <= new String(left).length(); i++) {
	            if (i < new String(left).length()
	                    && new String(left).charAt(i) != new String(right).charAt(i)) {
	                length++;
	                if (offset < 0) {
	                    offset = i;
	                }
	            } else if (offset != -1) {
	            	LOG.info("Offest{} ",offset);
	            	offsets.add(String.valueOf(offset) + "-" + String.valueOf(length));
	                length = 0;
	                offset = -1;
	            }
	        }
		} else if (criteria.equals(DiffCriteria.TEXT)) {
			LOG.info("Same size Doc but content are different, Finding Diffin based on text content between LEFT and RIGHT");
			String[] s1 = new String(Base64.getDecoder().decode(left)).split(" ");
			String[] s2 = new String(Base64.getDecoder().decode(right)).split(" ");
			for (int i = 0; i < s1.length; i++) {
				if (s1[i].equals(s2[i]))
					continue;
				else {
					LOG.info("DiffWord{} Length{}",s1[i],length);
					offsets.add(String.valueOf(i) + "-" + String.valueOf(s1.length));
				}
			}
		}
		
        return ResponseDTO.builder()
                .result(DiffResult.SAME_SIZE_BUT_DIFFNT_CONTENT)
                .offsets(offsets)
                .build();
	}

	@Override
	public String addDoc(String diffId, String file, Side side) throws InvalidFileException, SideIsEmptyException {
		LOG.info("Adding the {} to the {} with the content {}", side.toString(), diffId, file);
		
        if (!notValidBase64(file)) {
            throw new InvalidFileException();
        }

        DiffDocument doc = new DiffDocument(); 
		doc.setDiffid(diffId);
		doc.setDoc(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		doc.setSide(side.toString());
		doc = diffRepo.insert(doc);
       return doc.getId();
	}

	private boolean notValidBase64(String file) {
		try {
            Base64.getDecoder().decode(file);
        } catch (IllegalArgumentException e) {
            LOG.debug("The content {} is not a valid base64", file, e);
            return false;
        }
        return true;
	}

}
