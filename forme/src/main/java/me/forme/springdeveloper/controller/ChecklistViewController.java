package me.forme.springdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.forme.springdeveloper.dto.ChecklistViewResponse;
import me.forme.springdeveloper.service.ChecklistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChecklistViewController {

    private final ChecklistService checklistService;

    @GetMapping("/checklists")
    public String getChecklist(Model model) {
        List<ChecklistViewResponse> checklist = checklistService.findAll()
                .stream()
                .map(ChecklistViewResponse::new)
                .toList();
        model.addAttribute("checklist", checklist);

        return "";
    }

}