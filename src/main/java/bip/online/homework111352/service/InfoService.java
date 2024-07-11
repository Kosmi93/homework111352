package bip.online.homework111352.service;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class InfoService {

    public Object getSum() {

        return IntStream.iterate(1, a -> a +1).limit(1_000_000).reduce(0, (a, b) -> a + b );
    }
}
