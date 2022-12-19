package _20220926;

import java.io.IOException;
import java.util.List;

public interface Commandable {
    void runCommand(List<String> filenaems) throws IOException;
}
