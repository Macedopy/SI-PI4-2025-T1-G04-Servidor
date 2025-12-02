package construction.foundation;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FoundationRepository implements PanacheRepositoryBase<Foundation ,String> {
}