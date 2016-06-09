package com.deathrayresearch.outlier.filter.columnbased;

import com.deathrayresearch.outlier.Relation;
import com.deathrayresearch.outlier.columns.*;
import com.deathrayresearch.outlier.filter.ColumnFilter;
import com.google.common.base.Preconditions;
import org.roaringbitmap.RoaringBitmap;

/**
 *
 */
public class ColumnEqualTo extends ColumnFilter {

  private final ColumnReference otherColumn;

  public ColumnEqualTo(ColumnReference a, ColumnReference b) {
    super(a);
    otherColumn = b;
  }

  public RoaringBitmap apply(Relation relation) {

    Column column = relation.column(columnReference().getColumnName());
    Column other = relation.column(otherColumn.getColumnName());

    Preconditions.checkArgument(column.type() == other.type());

    if (column.type() == ColumnType.INTEGER)
      return apply((IntColumn) column, (IntColumn) other);

    if (column.type() == ColumnType.LONG_INT)
      return apply((LongColumn) column, (LongColumn) other);

    if (column.type() == ColumnType.SHORT_INT)
      return apply((ShortColumn) column, (ShortColumn) other);

    throw new UnsupportedOperationException("Not yet implemented for this column type");
  }

  private static RoaringBitmap apply(IntColumn column1, IntColumn column2) {
    return column1.isEqualTo(column2);
  }

  private static RoaringBitmap apply(ShortColumn column1, ShortColumn column2) {
    return column1.isEqualTo(column2);
  }

  private static RoaringBitmap apply(LongColumn column1, LongColumn column2) {
    return column1.isEqualTo(column2);
  }
}
