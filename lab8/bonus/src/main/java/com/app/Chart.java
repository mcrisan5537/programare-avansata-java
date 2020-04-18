package com.app;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// charts table;
// CREATE TABLE charts(
//        id INTEGER NOT NULL PRIMARY KEY,
//        artist_id INTEGER NOT NULL REFERENCES artists(id),
//        album_id INTEGER NOT NULL REFERENCES albums(id),
//        position INTEGER
// );

public class Chart {

    private List<ChartEntry> chartEntries = new ArrayList<>();

    public Chart() {
    }

    public Chart(List<ChartEntry> chartEntries) {
        this.chartEntries = chartEntries;
    }

    public ChartEntry getEntryByPosition(int position) {
        return chartEntries.get(position);
    }

    public boolean add(ChartEntry entry) {
        return chartEntries.add(entry);
    }

    public int size() {
        return chartEntries.size();
    }

    public List<ChartEntry> getChartEntries() {
        return chartEntries;
    }
}
