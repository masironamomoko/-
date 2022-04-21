package com.compe.competition_demo1.service;

import com.compe.competition_demo1.cdata.thesis_io.thesis_add_in;
import com.compe.competition_demo1.cdata.thesis_io.thesis_idsearch_out;

public interface ThesisService {
    int AddThesis(thesis_add_in thesis);
    int DeleteThesis(Integer id);
    thesis_idsearch_out IdSearch(Integer id);
}
