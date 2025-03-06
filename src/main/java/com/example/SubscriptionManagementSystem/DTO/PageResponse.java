package com.example.SubscriptionManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private int total;
    private int current;
    public PageResponse(Page<T> page){
        this.content = page.getContent();
        this.total = page.getTotalPages();
        this.current = page.getNumber();
    }
}
