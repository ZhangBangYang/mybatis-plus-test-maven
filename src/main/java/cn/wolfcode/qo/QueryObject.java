package cn.wolfcode.qo;


import lombok.Data;

@Data
public class QueryObject{
    private Integer currentPage = 1;
    private Integer pageSize = 5;
}
