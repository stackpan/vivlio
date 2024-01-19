package com.ivanzkyanto.vivlio.repository.gateway;

import java.util.List;

public interface BookReviewGatewayRepository {

    List<String> findReviewsById(String id);

    String addReviewById(String id, String review);

}
