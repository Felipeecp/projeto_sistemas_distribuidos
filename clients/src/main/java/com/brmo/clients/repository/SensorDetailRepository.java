package com.brmo.clients.repository;

import com.brmo.clients.domain.ClimateClassification;
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

    @Aggregation(pipeline = {
            "{ $addFields: { convertedDate: { $toDate: '$registrationDate' } } }",
            "{ $group: { _id: { codWMO: '$codWMO', year: { $year: '$convertedDate' }, month: { $month: '$convertedDate' }, day: { $dayOfMonth: '$convertedDate' } }, mediaTemperatura: { $avg: '$data.temperatura' }, mediaUmidade: { $avg: '$data.umidade' } } }",
            "{ $addFields: { classificacaoTemperatura: { $cond: { if: { $gt: ['$mediaTemperatura', 20] }, then: 'Quente', else: 'Frio' } }, classificacaoUmidade: { $cond: { if: { $gt: ['$mediaUmidade', 55] }, then: 'Úmido', else: 'Seco' } } } }",
            "{ $addFields: { classificacaoClima: { $concat: ['$classificacaoTemperatura', ' e ', '$classificacaoUmidade'] } } }",
            "{ $group: { _id: '$classificacaoClima', quantidade: { $sum: 1 } } }",
            "{ $project: { classe: '$_id', quantidade: 1, _id: 0 } }"
    })
    List<ClimateClassification> countByTemperatureAndHumidityCategories();


    List<SensorDetail> findTop10ByCodWMOOrderByRegistrationDateDesc(String codWMO);

}
