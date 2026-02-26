import java.util.UUID;

public class IdServiceImpl implements IdService{
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
