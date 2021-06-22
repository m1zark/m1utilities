/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.flowpowered.math.TrigMath
 *  com.flowpowered.math.vector.Vector3d
 */
package com.m1zark.m1utilities.api.Math;

import com.flowpowered.math.TrigMath;
import com.flowpowered.math.vector.Vector3d;

public class VectorMath {
    public static final float TAU = (float)Math.PI * 2;

    public static Vector3d rotateAroundAxisX(Vector3d v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return Vector3d.from((double)v.getX(), (double)y, (double)z);
    }

    public static Vector3d rotateAroundAxisY(Vector3d v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return Vector3d.from((double)x, (double)v.getY(), (double)z);
    }

    public static Vector3d rotateAroundAxisZ(Vector3d v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos - v.getY() * sin;
        double y = v.getX() * sin + v.getY() * cos;
        return Vector3d.from((double)x, (double)y, (double)v.getZ());
    }

    public static Vector3d rotateVector(Vector3d v, double angleX, double angleY, double angleZ) {
        VectorMath.rotateAroundAxisX(v, angleX);
        VectorMath.rotateAroundAxisY(v, angleY);
        VectorMath.rotateAroundAxisZ(v, angleZ);
        return v;
    }

    public static Vector3d getPointForCircle(float radians, double radius) {
        return Vector3d.from((double)((double)VectorMath.cos(radians) * radius), (double)0.0, (double)((double)(-VectorMath.sin(radians)) * radius));
    }

    public static Vector3d[] getLine(Vector3d start, Vector3d end, int points) {
        Vector3d[] result = new Vector3d[points];
        Vector3d link = end.sub(start);
        float len = (float)link.length();
        float ratio = len / (float)points;
        link = link.normalize();
        link = link.mul(ratio);
        result[0] = new Vector3d(start.add(link));
        for (int i = 1; i < points; ++i) {
            result[i] = result[i - 1].add(link);
        }
        return result;
    }

    public static float sin(float radians) {
        return TrigMath.sin((double)(radians % ((float)Math.PI * 2)));
    }

    public static float cos(float radians) {
        return TrigMath.cos((double)(radians % ((float)Math.PI * 2)));
    }
}

