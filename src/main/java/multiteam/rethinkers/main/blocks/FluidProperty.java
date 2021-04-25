package multiteam.rethinkers.main.blocks;

import com.google.common.collect.ImmutableSet;
import net.minecraft.state.Property;

import java.util.Collection;
import java.util.Optional;

public class FluidProperty extends Property<String> {
    public static final FluidProperty EMPTY = FluidProperty.create("empty");

    private final ImmutableSet<String> values = ImmutableSet.of("");

    protected FluidProperty(String string) {
        super(string, String.class);
    }

    @Override
    public Collection<String> getPossibleValues() {
        return this.values;
    }

    public static FluidProperty create(String string) {
        return new FluidProperty(string);
    }

    @Override
    public String getName(String name) {
        return name;
    }

    @Override
    public Optional<String> getValue(String value) {
        if(value.isEmpty()){
            return Optional.empty();
        }else{
            return Optional.of(value);
        }
    }
}
