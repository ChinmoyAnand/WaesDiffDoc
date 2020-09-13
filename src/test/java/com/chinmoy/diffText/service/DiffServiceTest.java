package com.chinmoy.diffText.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.chinmoy.diffText.entity.DiffDocument;
import com.chinmoy.diffText.model.DiffCriteria;
import com.chinmoy.diffText.model.DiffResult;
import com.chinmoy.diffText.model.ResponseDTO;
import com.chinmoy.diffText.model.Side;
import com.chinmoy.diffText.repository.DiffRepository;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.util.Optional;

@SpringBootTest
public class DiffServiceTest {

	@MockBean
	private DiffRepository diffRepository;

	@Autowired
	private DiffService service;
	
	DiffDocument docLeft = new DiffDocument("1L","1",new Binary(BsonBinarySubType.BINARY, "d2UgYXJlIGdvb2Q=".getBytes()), "LEFT");
	Optional<DiffDocument> opdocLeft = Optional.of(docLeft);
	
	DiffDocument docRight = new DiffDocument("1L","1",new Binary(BsonBinarySubType.BINARY, "YXJlIHdlIGdvb2Q=".getBytes()), "RIGHT");
	Optional<DiffDocument> opdocRight = Optional.of(docRight);

	
	@Test
	void testobjSave(@Autowired MongoTemplate mongoTemplate) {
        DBObject objectToSave = BasicDBObjectBuilder.start()
            .add("key", "value")
            .get();
        mongoTemplate.save(objectToSave, "collection");
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
            .containsOnly("value");
	}
	
	  @Test
	  void testfindByIdAndSideDoc(){
		Mockito.when(diffRepository.findByDiffidAndSide(docLeft.getDiffid(), Side.LEFT)).thenReturn(opdocLeft);
		Mockito.when(diffRepository.findByDiffidAndSide(docLeft.getDiffid(), Side.RIGHT)).thenReturn(opdocLeft);
	    ResponseDTO  dto = service.getDiffById("1", DiffCriteria.DEFAULT);
	    assertThat(dto.getResult()).isEqualTo(DiffResult.EQUALS);
	  }
	  
	  @Test
	  void testfindByIdAndSideDocDiff(){
		Mockito.when(diffRepository.findByDiffidAndSide(docLeft.getDiffid(), Side.LEFT)).thenReturn(opdocLeft);
		Mockito.when(diffRepository.findByDiffidAndSide(docLeft.getDiffid(), Side.RIGHT)).thenReturn(opdocRight);
	    ResponseDTO  dto = service.getDiffById("1", DiffCriteria.DEFAULT);
	    assertThat(dto.getResult()).isEqualTo(DiffResult.SAME_SIZE_BUT_DIFFNT_CONTENT);
	  }
	  
	  @Test
	  void testfindByIdAndSideDocDiffByText(){
		Mockito.when(diffRepository.findByDiffidAndSide(docLeft.getDiffid(), Side.LEFT)).thenReturn(opdocLeft);
		Mockito.when(diffRepository.findByDiffidAndSide(docLeft.getDiffid(), Side.RIGHT)).thenReturn(opdocRight);
	    ResponseDTO  dto = service.getDiffById("1", DiffCriteria.TEXT);
	    assertThat(dto.getResult()).isEqualTo(DiffResult.SAME_SIZE_BUT_DIFFNT_CONTENT);
	  }

}
