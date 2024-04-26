package net.technic.snow_update.worldgen.area;

public interface AreaTransformer0 {


    default AreaFactory run(AreaContext context) {
        return () -> {
            return context.createResult((x, y) -> {
                context.initRandom((long)x, (long)y);
                return this.apply(context, x, y);
            });
        };
    }

    int apply(AreaContext context, int x, int y);
}
