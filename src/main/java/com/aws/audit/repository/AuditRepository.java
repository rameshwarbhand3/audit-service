package com.aws.audit.repository;

import com.aws.audit.dto.SearchCriteria;
import com.aws.audit.entity.Audit;

import java.util.List;

public interface AuditRepository {
    void save(Audit audit);
    List<Audit> search(SearchCriteria searchCriteria);
}
