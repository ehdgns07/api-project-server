package com.nhnacademy.springboot.apiprojectserver.service.Comment;

import com.nhnacademy.springboot.apiprojectserver.domain.comment.CommentDto;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.DeleteCommentDto;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.NewCommentRequest;
import com.nhnacademy.springboot.apiprojectserver.domain.comment.EditCommentDto;
import com.nhnacademy.springboot.apiprojectserver.entity.Comment;
import com.nhnacademy.springboot.apiprojectserver.entity.Member;
import com.nhnacademy.springboot.apiprojectserver.entity.Task;
import com.nhnacademy.springboot.apiprojectserver.repository.comment.CommentRepository;
import com.nhnacademy.springboot.apiprojectserver.repository.member.MemberRepository;
import com.nhnacademy.springboot.apiprojectserver.repository.task.TaskRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    private final MemberRepository memberRepository;

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;


    @Override
    public CommentDto createComment(NewCommentRequest newCommentRequest) {

        Member member = memberRepository.findById(newCommentRequest.getMemberId()).orElse(null);
        Task task = taskRepository.findByPkTaskId(newCommentRequest.getPkTaskId());


        Comment comment = Comment.builder()
            .commentContent(newCommentRequest.getCommentContent())
            .createdDt(LocalDateTime.now())
            .member(member).build();

        task.addComment(comment);

        return modelMapper.map(commentRepository.save(comment), NewCommentRequest.class);
    }

    @Override
    public CommentDto modifyComment(EditCommentDto editCommentDto) {
        Comment comment = commentRepository.findById(editCommentDto.getCommentId()).orElse(null);

        if(Objects.isNull(comment)){
            return null;
        }

        if(Objects.equals(comment.getMember().getMemberId(), editCommentDto.getMemberId())){

            comment.setCommentContent(editCommentDto.getCommentContent());
            comment.setModifiedDt(LocalDateTime.now());

            return modelMapper.map(commentRepository.save(comment), EditCommentDto.class);
        }

        return null;
    }

    @Override
    public String removeComment(DeleteCommentDto deleteCommentDto) {
        Comment comment = commentRepository.findById(deleteCommentDto.getCommentId()).orElse(null);

        if(Objects.isNull(comment)){
            return "{\"messge\": \"댓글이 존재하지 않습니다.\"}";
        }

        if(!Objects.equals(comment.getMember().getMemberId(), deleteCommentDto.getMemberId())){
            return "{\"messge\": \"작성자가 아닙니다.\"}";
        }

        commentRepository.deleteById(deleteCommentDto.getCommentId());

        return "{\"messge\": \"" + deleteCommentDto.getCommentId() + "번 댓글이 삭제되었습니다.\"}";
    }
}
