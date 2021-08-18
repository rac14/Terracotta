package org.terracottamc.math;

/**
 * Copyright (c) 2021, TerracottaMC
 * All rights reserved.
 *
 * <p>
 * This project is licensed under the BSD 3-Clause License which
 * can be found in the root directory of this source tree
 *
 * @author Kaooot
 * @version 1.0
 */
public class Vector {

    private float x;
    private float y;
    private float z;

    /**
     * Creates a new {@link org.terracottamc.math.Vector}
     *
     * @param x which represents the value on the x-axis
     * @param y which represents the value on the y-axis
     * @param z which represents the value on the z-axis
     */
    public Vector(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Retrieves the upper {@link org.terracottamc.math.Vector}
     *
     * @return a fresh {@link org.terracottamc.math.Vector}
     */
    public static Vector up() {
        return new Vector(0, 1, 0);
    }

    /**
     * Retrieves the lower {@link org.terracottamc.math.Vector}
     *
     * @return a fresh {@link org.terracottamc.math.Vector}
     */
    public static Vector down() {
        return new Vector(0, -1, 0);
    }

    /**
     * Retrieves the north direction {@link org.terracottamc.math.Vector}
     *
     * @return a fresh {@link org.terracottamc.math.Vector}
     */
    public static Vector north() {
        return new Vector(0, 0, -1);
    }

    /**
     * Retrieves the east direction {@link org.terracottamc.math.Vector}
     *
     * @return a fresh {@link org.terracottamc.math.Vector}
     */
    public static Vector east() {
        return new Vector(1, 0, 0);
    }

    /**
     * Retrieves the south direction {@link org.terracottamc.math.Vector}
     *
     * @return a fresh {@link org.terracottamc.math.Vector}
     */
    public static Vector south() {
        return new Vector(0, 0, 1);
    }

    /**
     * Retrieves the west direction {@link org.terracottamc.math.Vector}
     *
     * @return a fresh {@link org.terracottamc.math.Vector}
     */
    public static Vector west() {
        return new Vector(-1, 0, 0);
    }

    /**
     * Adds new coordinates to this {@link org.terracottamc.math.Vector}
     *
     * @param x coordinate that should be added
     * @param y coordinate that should be added
     * @param z coordinate that should be added
     *
     * @return a fresh calculated {@link org.terracottamc.math.Vector}
     */
    public Vector add(final float x, final float y, final float z) {
        return new Vector(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Subtracts new coordinates from this {@link org.terracottamc.math.Vector}
     *
     * @param x coordinate that should be subtracted
     * @param y coordinate that should be subtracted
     * @param z coordinate that should be subtracted
     *
     * @return a fresh calculated {@link org.terracottamc.math.Vector}
     */
    public Vector subtract(final float x, final float y, final float z) {
        return new Vector(this.x - x, this.y - y, this.z - z);
    }

    /**
     * Multiplies new coordinates to this {@link org.terracottamc.math.Vector}
     *
     * @param x coordinate that should be multiplied
     * @param y coordinate that should be multiplied
     * @param z coordinate that should be multiplied
     *
     * @return a fresh calculated {@link org.terracottamc.math.Vector}
     */
    public Vector multiply(final float x, final float y, final float z) {
        return new Vector(this.x * x, this.y * y, this.z * z);
    }

    /**
     * Divides new coordinates of this {@link org.terracottamc.math.Vector}
     *
     * @param x coordinate that should be divided
     * @param y coordinate that should be divided
     * @param z coordinate that should be divided
     *
     * @return a fresh calculated {@link org.terracottamc.math.Vector}
     */
    public Vector divide(final float x, final float y, final float z) {
        return new Vector(this.x / x, this.y / y, this.z / z);
    }

    /**
     * Creates the square root from the coordinates of this {@link org.terracottamc.math.Vector}
     *
     * @return a fresh calculated {@link org.terracottamc.math.Vector}
     */
    public Vector squareRoot() {
        return new Vector((float) Math.sqrt(this.x), (float) Math.sqrt(this.y), (float) Math.sqrt(this.z));
    }

    /**
     * Creates the cubic root from the coordinates of this {@link org.terracottamc.math.Vector}
     *
     * @return a fresh calculated {@link org.terracottamc.math.Vector}
     */
    public Vector cubicRoot() {
        return new Vector((float) Math.cbrt(this.x), (float) Math.cbrt(this.y), (float) Math.cbrt(this.z));
    }

    /**
     * Normalizes this {@link org.terracottamc.math.Vector} by dividing its values through the
     * square root of the sum of its squared values
     *
     * @return a fresh normalized {@link org.terracottamc.math.Vector}
     */
    public Vector normalize() {
        final float squaredLength = this.squaredLength();

        return this.divide(squaredLength, squaredLength, squaredLength);
    }

    /**
     * Calculates the distance between this and a given {@link org.terracottamc.math.Vector}
     *
     * @param vector which represents the endpoint that is used in the calculation
     *
     * @return a fresh calculated distance float
     */
    public float distance(final Vector vector) {
        return (float) Math.sqrt(this.squaredDistance(vector));
    }

    /**
     * Calculates the distance between this and a given {@link org.terracottamc.math.Vector} and squares its values
     *
     * @param vector which is used to execute the calculation
     *
     * @return a fresh calculated and squared distance
     */
    public float squaredDistance(final Vector vector) {
        return (float) (Math.pow((this.x - vector.getX()), 2) + Math.pow((this.y - vector.getY()), 2) +
                Math.pow((this.z - vector.getZ()), 2));
    }

    /**
     * Calculates the square root of the sum of the squared values from this {@link org.terracottamc.math.Vector}
     *
     * @return a fresh squared length
     */
    public float squaredLength() {
        return (float) (Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z));
    }

    /**
     * Retrieves the x value of this {@link org.terracottamc.math.Vector}
     *
     * @return a fresh float value
     */
    public float getX() {
        return this.x;
    }

    /**
     * Retrieves the y value of this {@link org.terracottamc.math.Vector}
     *
     * @return a fresh float value
     */
    public float getY() {
        return this.y;
    }

    /**
     * Retrieves the z value of this {@link org.terracottamc.math.Vector}
     *
     * @return a fresh float value
     */
    public float getZ() {
        return this.z;
    }

    /**
     * Updates the x value of this {@link org.terracottamc.math.Vector}
     *
     * @param x that represents the updated value
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Updates the y value of this {@link org.terracottamc.math.Vector}
     *
     * @param y that represents the updated value
     */
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * Updates the z value of this {@link org.terracottamc.math.Vector}
     *
     * @param z that represents the updated value
     */
    public void setZ(final float z) {
        this.z = z;
    }
}