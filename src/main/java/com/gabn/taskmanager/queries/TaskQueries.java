package com.gabn.taskmanager.queries;

import com.gabn.taskmanager.models.TaskModel;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.gabn.taskmanager.constants.FieldConstants.CREATED_DATE;
import static com.gabn.taskmanager.constants.FieldConstants.DESCRIPTION;
import static com.gabn.taskmanager.constants.FieldConstants.ID;
import static com.gabn.taskmanager.constants.FieldConstants.NAME;
import static com.gabn.taskmanager.constants.FieldConstants.UPDATED_DATE;
import static com.gabn.taskmanager.constants.GeneralConstants.QUERY_KEY;
import static com.gabn.taskmanager.constants.GeneralConstants.UPDATE_KEY;
import static java.util.Map.entry;

@NoArgsConstructor
public final class TaskQueries {

    public static Query getFindAllQuery(TaskModel taskModel) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();
        if (Objects.nonNull(taskModel.getId())) {
            criteriaList.add(Criteria.where(ID).regex(taskModel.getId()));
        }
        if (Objects.nonNull(taskModel.getName())) {
            criteriaList.add(Criteria.where(NAME).regex(taskModel.getName()));
        }
        if (Objects.nonNull(taskModel.getDescription())) {
            criteriaList.add(Criteria.where(DESCRIPTION).regex(taskModel.getDescription()));
        }
        if (Objects.nonNull(taskModel.getCreatedDate()) && Objects.nonNull(taskModel.getUpdatedDate())) {
            criteriaList.add(
                Criteria.where(CREATED_DATE)
                    .gte(taskModel.getCreatedDate()).lt(taskModel.getUpdatedDate())
            );
        }
        if (!criteriaList.isEmpty()) {
            criteria.andOperator(criteriaList);
            query.addCriteria(criteria);
        }
        return query;
    }

    public static Map<String, Object> getQueryForUpdate(TaskModel taskModel) {
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(taskModel.getId()));
        Update update = new Update();
        if (Objects.nonNull(taskModel.getName())) {
            update.set(NAME, taskModel.getName());
        }
        if (Objects.nonNull(taskModel.getDescription())) {
            update.set(DESCRIPTION, taskModel.getDescription());
        }
        update.set(UPDATED_DATE, LocalDateTime.now(ZoneOffset.UTC));
        return Map.ofEntries(
            entry(QUERY_KEY, query),
            entry(UPDATE_KEY, update)
        );
    }
}
