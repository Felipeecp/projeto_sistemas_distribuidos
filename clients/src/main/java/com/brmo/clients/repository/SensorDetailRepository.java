package com.brmo.clients.repository;

import com.brmo.clients.domain.SensorDetail;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SensorDetailRepository extends MongoRepository<SensorDetail,String> {

    @Query(value = "{}", fields = "{'codWMO' : 1}")
    List<String> findDistinctCodWMO();

    List<SensorDetail> findAllByCodWMO(String codWMO);

    @Aggregation(pipeline = {
            "{ $match: { 'codWMO': ?0 } }",
            "{ $addFields: { 'day': { $dateToString: { format: '%Y-%m-%d', date: '$registrationDate' } } } }",
            "{ $sort: { 'registrationDate': 1 } }",
            "{ $group: { _id: '$day', firstRecord: { $first: '$$ROOT' } } }",
            "{ $replaceRoot: { newRoot: '$firstRecord' } }",
            "{ $limit: 7 }"
    })
    List<SensorDetail> findOnePerDay(String codWMO);

    List<SensorDetail> findTop10ByCodWMOOrderByRegistrationDateDesc(String codWMO);

}
