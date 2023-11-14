package com.example.todolistapp.utils;

import javafx.scene.control.TableView;

import java.util.concurrent.atomic.AtomicLong;

public class ResizeTable {
    public void customResize(TableView<?> view) {
        AtomicLong width = new AtomicLong();

        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }
}
