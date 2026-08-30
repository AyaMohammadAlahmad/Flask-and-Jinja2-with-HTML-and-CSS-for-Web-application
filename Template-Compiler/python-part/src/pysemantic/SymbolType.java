package pysemantic;

/**
 * أنواع الرموز الممكنة في جدول الرموز.
 *
 * VARIABLE → متغير عادي أو معامل دالة
 * FUNCTION → تعريف دالة
 * IMPORT   → رمز مستورد (from flask import Flask ...)
 */
public enum SymbolType {
    VARIABLE,
    FUNCTION,
    IMPORT
}
