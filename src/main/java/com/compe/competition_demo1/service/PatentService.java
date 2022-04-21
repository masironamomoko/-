package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.patent_io.patent_add_in;
import com.compe.competition_demo1.cdata.patent_io.patent_idsearch_out;

public interface PatentService {
    int AddPatent(patent_add_in patent);
    int DeletePatent(Integer id);
    patent_idsearch_out IdSearch(Integer id);
}
