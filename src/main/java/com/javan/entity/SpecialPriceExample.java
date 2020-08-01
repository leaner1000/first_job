package com.javan.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SpecialPriceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpecialPriceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCloth_idIsNull() {
            addCriterion("cloth_id is null");
            return (Criteria) this;
        }

        public Criteria andCloth_idIsNotNull() {
            addCriterion("cloth_id is not null");
            return (Criteria) this;
        }

        public Criteria andCloth_idEqualTo(Integer value) {
            addCriterion("cloth_id =", value, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCloth_idNotEqualTo(Integer value) {
            addCriterion("cloth_id <>", value, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCloth_idGreaterThan(Integer value) {
            addCriterion("cloth_id >", value, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCloth_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("cloth_id >=", value, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCloth_idLessThan(Integer value) {
            addCriterion("cloth_id <", value, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCloth_idLessThanOrEqualTo(Integer value) {
            addCriterion("cloth_id <=", value, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCloth_idIn(List<Integer> values) {
            addCriterion("cloth_id in", values, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCloth_idNotIn(List<Integer> values) {
            addCriterion("cloth_id not in", values, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCloth_idBetween(Integer value1, Integer value2) {
            addCriterion("cloth_id between", value1, value2, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCloth_idNotBetween(Integer value1, Integer value2) {
            addCriterion("cloth_id not between", value1, value2, "cloth_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idIsNull() {
            addCriterion("custom_id is null");
            return (Criteria) this;
        }

        public Criteria andCustom_idIsNotNull() {
            addCriterion("custom_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustom_idEqualTo(String value) {
            addCriterion("custom_id =", value, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idNotEqualTo(String value) {
            addCriterion("custom_id <>", value, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idGreaterThan(String value) {
            addCriterion("custom_id >", value, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idGreaterThanOrEqualTo(String value) {
            addCriterion("custom_id >=", value, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idLessThan(String value) {
            addCriterion("custom_id <", value, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idLessThanOrEqualTo(String value) {
            addCriterion("custom_id <=", value, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idLike(String value) {
            addCriterion("custom_id like", value, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idNotLike(String value) {
            addCriterion("custom_id not like", value, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idIn(List<String> values) {
            addCriterion("custom_id in", values, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idNotIn(List<String> values) {
            addCriterion("custom_id not in", values, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idBetween(String value1, String value2) {
            addCriterion("custom_id between", value1, value2, "custom_id");
            return (Criteria) this;
        }

        public Criteria andCustom_idNotBetween(String value1, String value2) {
            addCriterion("custom_id not between", value1, value2, "custom_id");
            return (Criteria) this;
        }

        public Criteria andDefault_priceIsNull() {
            addCriterion("default_price is null");
            return (Criteria) this;
        }

        public Criteria andDefault_priceIsNotNull() {
            addCriterion("default_price is not null");
            return (Criteria) this;
        }

        public Criteria andDefault_priceEqualTo(BigDecimal value) {
            addCriterion("default_price =", value, "default_price");
            return (Criteria) this;
        }

        public Criteria andDefault_priceNotEqualTo(BigDecimal value) {
            addCriterion("default_price <>", value, "default_price");
            return (Criteria) this;
        }

        public Criteria andDefault_priceGreaterThan(BigDecimal value) {
            addCriterion("default_price >", value, "default_price");
            return (Criteria) this;
        }

        public Criteria andDefault_priceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("default_price >=", value, "default_price");
            return (Criteria) this;
        }

        public Criteria andDefault_priceLessThan(BigDecimal value) {
            addCriterion("default_price <", value, "default_price");
            return (Criteria) this;
        }

        public Criteria andDefault_priceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("default_price <=", value, "default_price");
            return (Criteria) this;
        }

        public Criteria andDefault_priceIn(List<BigDecimal> values) {
            addCriterion("default_price in", values, "default_price");
            return (Criteria) this;
        }

        public Criteria andDefault_priceNotIn(List<BigDecimal> values) {
            addCriterion("default_price not in", values, "default_price");
            return (Criteria) this;
        }

        public Criteria andDefault_priceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_price between", value1, value2, "default_price");
            return (Criteria) this;
        }

        public Criteria andDefault_priceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_price not between", value1, value2, "default_price");
            return (Criteria) this;
        }

        public Criteria andUnused1IsNull() {
            addCriterion("unused1 is null");
            return (Criteria) this;
        }

        public Criteria andUnused1IsNotNull() {
            addCriterion("unused1 is not null");
            return (Criteria) this;
        }

        public Criteria andUnused1EqualTo(String value) {
            addCriterion("unused1 =", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1NotEqualTo(String value) {
            addCriterion("unused1 <>", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1GreaterThan(String value) {
            addCriterion("unused1 >", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1GreaterThanOrEqualTo(String value) {
            addCriterion("unused1 >=", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1LessThan(String value) {
            addCriterion("unused1 <", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1LessThanOrEqualTo(String value) {
            addCriterion("unused1 <=", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1Like(String value) {
            addCriterion("unused1 like", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1NotLike(String value) {
            addCriterion("unused1 not like", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1In(List<String> values) {
            addCriterion("unused1 in", values, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1NotIn(List<String> values) {
            addCriterion("unused1 not in", values, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1Between(String value1, String value2) {
            addCriterion("unused1 between", value1, value2, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1NotBetween(String value1, String value2) {
            addCriterion("unused1 not between", value1, value2, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused2IsNull() {
            addCriterion("unused2 is null");
            return (Criteria) this;
        }

        public Criteria andUnused2IsNotNull() {
            addCriterion("unused2 is not null");
            return (Criteria) this;
        }

        public Criteria andUnused2EqualTo(String value) {
            addCriterion("unused2 =", value, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2NotEqualTo(String value) {
            addCriterion("unused2 <>", value, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2GreaterThan(String value) {
            addCriterion("unused2 >", value, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2GreaterThanOrEqualTo(String value) {
            addCriterion("unused2 >=", value, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2LessThan(String value) {
            addCriterion("unused2 <", value, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2LessThanOrEqualTo(String value) {
            addCriterion("unused2 <=", value, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2Like(String value) {
            addCriterion("unused2 like", value, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2NotLike(String value) {
            addCriterion("unused2 not like", value, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2In(List<String> values) {
            addCriterion("unused2 in", values, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2NotIn(List<String> values) {
            addCriterion("unused2 not in", values, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2Between(String value1, String value2) {
            addCriterion("unused2 between", value1, value2, "unused2");
            return (Criteria) this;
        }

        public Criteria andUnused2NotBetween(String value1, String value2) {
            addCriterion("unused2 not between", value1, value2, "unused2");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}