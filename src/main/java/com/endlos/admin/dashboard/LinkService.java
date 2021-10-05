package com.endlos.admin.dashboard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LinkService  {
	
	@Autowired
	LinkRepository linkrepository;
	public LinkModel linkmodel(LinkModel linkmodel) throws Exception
	{
		LinkModel link=linkrepository.save(linkmodel);
		if (link==null) {
			throw new Exception("Link not found");
		}
		return link;
	}
	public List<LinkModel> GetAllDAta(LinkModel linkmodel)
	{
		return linkrepository.findAll();
	}
	public Optional<LinkModel> FindById(int id)
	{
		return linkrepository.findById(id);
	}
//	public List<LinkModel> findByStatus(int s1,int s2)
//	{
//		return linkrepository.findByLstatusAnd(s1, s2);
//	}
}
