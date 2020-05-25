package ua.edu.ukma.ykrukovska.practice2.processor;

public interface Processor<I,O> {
    O process(I data);
}
