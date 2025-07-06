package com.example.parkee.model.response.api;

import com.example.parkee.model.response.wrapper.PageMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiListResponse<T> {

  private List<T> content;

  private PageMetaData pageMetaData;
}
