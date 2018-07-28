package com.amazon.market.dao;
 
import java.util.List;

import com.amazon.market.entity.Applicant;
import com.amazon.market.model.ApplicantInfo;
 
public interface ApplicantDAO {
 
    public Applicant findApplicant(String id);
 
    public List<ApplicantInfo> listApplicantInfos();
 
    public void saveApplicant(ApplicantInfo applicantInfo);
 
    public ApplicantInfo findApplicantInfo(String id);
 
    public void deleteApplicant(String id);
    
}