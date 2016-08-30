package com.burhan.webstore.domain.repository;

public interface SequenceGenerator{

	public String getNextSequence(String seqName);
}
