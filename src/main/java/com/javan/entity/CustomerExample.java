package com.javan.entity;

import java.util.ArrayList;
import java.util.List;

public class CustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerExample() {
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

        public Criteria andCustomer_idIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomer_idIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomer_idEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_idNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_idGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_idLessThan(Integer value) {
            addCriterion("customer_id <", value, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_idLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_idIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_idNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_idBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_idNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customer_id");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameEqualTo(String value) {
            addCriterion("customer_name =", value, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameLessThan(String value) {
            addCriterion("customer_name <", value, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameLike(String value) {
            addCriterion("customer_name like", value, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameNotLike(String value) {
            addCriterion("customer_name not like", value, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameIn(List<String> values) {
            addCriterion("customer_name in", values, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customer_name");
            return (Criteria) this;
        }

        public Criteria andCustomer_nameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customer_name");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPhone_numberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhone_numberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhone_numberEqualTo(String value) {
            addCriterion("phone_number =", value, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberLessThan(String value) {
            addCriterion("phone_number <", value, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberLike(String value) {
            addCriterion("phone_number like", value, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberNotLike(String value) {
            addCriterion("phone_number not like", value, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberIn(List<String> values) {
            addCriterion("phone_number in", values, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phone_number");
            return (Criteria) this;
        }

        public Criteria andPhone_numberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phone_number");
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

        public Criteria andUnused1EqualTo(Integer value) {
            addCriterion("unused1 =", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1NotEqualTo(Integer value) {
            addCriterion("unused1 <>", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1GreaterThan(Integer value) {
            addCriterion("unused1 >", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1GreaterThanOrEqualTo(Integer value) {
            addCriterion("unused1 >=", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1LessThan(Integer value) {
            addCriterion("unused1 <", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1LessThanOrEqualTo(Integer value) {
            addCriterion("unused1 <=", value, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1In(List<Integer> values) {
            addCriterion("unused1 in", values, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1NotIn(List<Integer> values) {
            addCriterion("unused1 not in", values, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1Between(Integer value1, Integer value2) {
            addCriterion("unused1 between", value1, value2, "unused1");
            return (Criteria) this;
        }

        public Criteria andUnused1NotBetween(Integer value1, Integer value2) {
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