package com.MyBlogApplication.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
// for returning the page no. page size and pageNo and available content
@Data
@AllArgsConstructor
@NoArgsConstructor
public class postResponse {
   private List<PostDto> dto;
   private int pageSize;
   private int pageNo;
   private boolean lastPage;
   private int totalPage;

}
