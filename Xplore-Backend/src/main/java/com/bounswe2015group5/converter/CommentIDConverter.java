package com.bounswe2015group5.converter;

import com.bounswe2015group5.model.Comment;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;


@Configuration
public class CommentIDConverter implements Converter<String,Comment.CommentId> {
    @Override
    public Comment.CommentId convert(String s) {
        String[] str = s.split("_");
        String username = str[0];
        Integer contributionId = Integer.parseInt(str[1]);
        return new Comment.CommentId(username,contributionId);
    }
}