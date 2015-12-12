package com.bounswe2015group5.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import static com.bounswe2015group5.model.Relation.RelationID;

@Configuration
public class RelationIDConverter implements Converter<String,RelationID> {
    @Override
    public RelationID convert(String s) {
        String[] integers = s.split("_");
        Integer tagId = Integer.parseInt(integers[0]);
        Integer contributionId = Integer.parseInt(integers[1]);
        return new RelationID(tagId,contributionId);
    }
}