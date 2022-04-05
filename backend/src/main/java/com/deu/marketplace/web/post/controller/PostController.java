package com.deu.marketplace.web.post.controller;

import com.deu.marketplace.common.PostSearchCond;
import com.deu.marketplace.domain.postComment.entity.PostComment;
import com.deu.marketplace.domain.postComment.service.PostCommentService;
import com.deu.marketplace.query.postListView.dto.PostDetailViewDto;
import com.deu.marketplace.query.postListView.dto.PostListViewDto;
import com.deu.marketplace.query.postListView.repository.PostViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostViewRepository postViewRepository;
    private final PostCommentService postCommentService;

    @GetMapping
    public ResponseEntity<?> getPostList(PostSearchCond cond,
                                         @PageableDefault(size = 20, page = 0,
                                                 sort = "lastModifiedDate",
                                                 direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostListViewDto> postsPages = postViewRepository.getPostsPages(cond, pageable);
        return ResponseEntity.ok().body(postsPages);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getOnePost(@PathVariable("postId") Long postId,
                                        @AuthenticationPrincipal Long memberId) {
        PostDetailViewDto postDetailViewDto =
                postViewRepository.getPostDetail(postId, memberId).orElseThrow();

        return ResponseEntity.ok().body(postDetailViewDto);
    }

//    @PatchMapping("/{postId}")
//    public ResponseEntity<?> updateOnePost(@PathVariable("postId") Long postId) {}
//
//    @DeleteMapping("/{postId}")
//    public ResponseEntity<?> deleteOnePost(@PathVariable("postId") Long postId) {}
//
//    @PostMapping("/save")
//    public ResponseEntity<?> savePost(@RequestBody Dto dto) {}
}