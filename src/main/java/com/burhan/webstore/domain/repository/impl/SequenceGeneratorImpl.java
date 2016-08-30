package com.burhan.webstore.domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.burhan.webstore.domain.repository.SequenceGenerator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Component(value = "seqGenerator")
public class SequenceGeneratorImpl implements SequenceGenerator {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public String getNextSequence(String seqName) {
		BasicDBObject find = new BasicDBObject();
		find.put("_id", seqName);
		DBCollection collection = mongoTemplate.getCollection("counters");
		BasicDBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("seq", 1));
		DBObject obj = collection.findAndModify(find, update);
		return obj.get("seq").toString();
	}

}
