package com.example.jwt.dto;

import com.example.jwt.entity.Blog;
import lombok.RequiredArgsConstructor;

public interface BlogPublic {
    Integer getId();

    String getTitle();

    String getSlug();

    String getDescription();

    String getContent();

    String getThumbnail();

    String getCreatedAt();

    String getUpdatedAt();

    String getPublishedAt();

    Boolean getStatus();

    @RequiredArgsConstructor
    class BlogPublicImpl implements BlogPublic {
        private final Blog blog;

        @Override
        public Integer getId() {
            return null;
        }

        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public String getSlug() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public String getContent() {
            return null;
        }

        @Override
        public String getThumbnail() {
            return null;
        }

        @Override
        public String getCreatedAt() {
            return null;
        }

        @Override
        public String getUpdatedAt() {
            return null;
        }

        @Override
        public String getPublishedAt() {
            return null;
        }

        @Override
        public Boolean getStatus() {
            return null;
        }
    }
}
