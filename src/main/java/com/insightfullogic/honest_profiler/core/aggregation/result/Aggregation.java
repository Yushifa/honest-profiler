package com.insightfullogic.honest_profiler.core.aggregation.result;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.insightfullogic.honest_profiler.core.aggregation.AggregationProfile;
import com.insightfullogic.honest_profiler.core.aggregation.filter.FilterSpecification;
import com.insightfullogic.honest_profiler.core.profiles.lean.LeanNode;
import com.insightfullogic.honest_profiler.core.profiles.lean.NumericInfo;

public class Aggregation<K, T extends Keyed<K>>
{
    private final AggregationProfile source;
    private final List<T> data;
    private LeanNode reference;

    public Aggregation(AggregationProfile source, List<T> data, LeanNode reference)
    {
        super();
        this.source = source;
        this.data = data;
        this.reference = reference;
    }

    public AggregationProfile getSource()
    {
        return source;
    }

    public LeanNode getReference()
    {
        return reference;
    }

    public NumericInfo getReferenceData()
    {
        return reference.getData();
    }

    public void setReference(LeanNode reference)
    {
        this.reference = reference;
    }

    public List<T> getData()
    {
        return data;
    }

    public Aggregation<K, T> filter(FilterSpecification<T> filterSpec)
    {
        return new Aggregation<>(
            source,
            data.stream().filter(filterSpec.getFilter()).collect(toList()),
            reference);
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append("Aggregation :\n");
        data.forEach(result::append);
        return result.toString();
    }
}
