package net.technic.snow_update.worldgen.area;

public interface AreaTransformer1 {
    default AreaFactory run(AreaContext context, AreaFactory areaFactory)
    {
        return () -> {
            Area area = areaFactory.make();
            return context.createResult((x, y) -> {
                context.initRandom((long)x, (long)y);
                return this.apply(context, area, x, y);
            }, area);
        };
    }

    int apply(AreaContext context, Area area, int x, int y);
}
