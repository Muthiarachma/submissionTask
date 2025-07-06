package com.example.parkee.model.response.wrapper;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PageMetaData implements Serializable {

  private static final long serialVersionUID = 6927667702020828247L;

  private long pageNumber;

  private long pageSize;

  private long totalRecords;
}
