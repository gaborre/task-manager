package com.gabn.taskmanager.queries;

import com.gabn.taskmanager.models.UserModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gabn.taskmanager.constants.FieldConstants.*;
import static com.gabn.taskmanager.constants.GeneralConstants.QUERY_KEY;
import static com.gabn.taskmanager.constants.GeneralConstants.UPDATE_KEY;
import static java.util.Map.entry;

public class UserQueries {

    public static Query getFindAllQuery(UserModel userModel) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();
        if (Objects.nonNull(userModel.getIdentification())) {
            criteriaList.add(Criteria.where(IDENTIFICATION).is(userModel.getIdentification()));
        }
        if (Objects.nonNull(userModel.getName())) {
            criteriaList.add(Criteria.where(NAME).regex(userModel.getName()));
        }
        if (Objects.nonNull(userModel.getLastName())) {
            criteriaList.add(Criteria.where(LAST_NAME).regex(userModel.getLastName()));
        }
        if (Objects.nonNull(userModel.getAddress())) {
            criteriaList.add(Criteria.where(ADDRESS).regex(userModel.getAddress()));
        }
        if (Objects.nonNull(userModel.getPhone())) {
            criteriaList.add(Criteria.where(PHONE).regex(userModel.getPhone()));
        }
        if (Objects.nonNull(userModel.getCreatedDate()) && Objects.nonNull(userModel.getUpdatedDate())) {
            criteriaList.add(
                Criteria.where(CREATED_DATE)
                    .gte(userModel.getCreatedDate()).lt(userModel.getUpdatedDate())
            );
        }
        if (!criteriaList.isEmpty()) {
            criteria.andOperator(criteriaList);
            query.addCriteria(criteria);
        }
        return query;
    }

    public static Map<String, Object> getQueryForUpdate(UserModel userModel) {
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(userModel.getId()));
        Update update = new Update();
        if (Objects.nonNull(userModel.getIdentification())) {
            update.set(IDENTIFICATION, userModel.getIdentification());
        }
        if (Objects.nonNull(userModel.getName())) {
            update.set(NAME, userModel.getName());
        }
        if (Objects.nonNull(userModel.getLastName())) {
            update.set(LAST_NAME, userModel.getLastName());
        }
        if (Objects.nonNull(userModel.getAddress())) {
            update.set(ADDRESS, userModel.getAddress());
        }
        if (Objects.nonNull(userModel.getPhone())) {
            update.set(PHONE, userModel.getPhone());
        }
        update.set(UPDATED_DATE, LocalDateTime.now(ZoneOffset.UTC));
        return Map.ofEntries(
            entry(QUERY_KEY, query),
            entry(UPDATE_KEY, update)
        );
    }
}
