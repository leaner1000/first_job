package com.javan.entity;

import java.util.ArrayList;
import java.util.List;

public class ClothExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClothExample() {
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

        public Criteria andPicture_pathIsNull() {
            addCriterion("picture_path is null");
            return (Criteria) this;
        }

        public Criteria andPicture_pathIsNotNull() {
            addCriterion("picture_path is not null");
            return (Criteria) this;
        }

        public Criteria andPicture_pathEqualTo(String value) {
            addCriterion("picture_path =", value, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathNotEqualTo(String value) {
            addCriterion("picture_path <>", value, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathGreaterThan(String value) {
            addCriterion("picture_path >", value, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathGreaterThanOrEqualTo(String value) {
            addCriterion("picture_path >=", value, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathLessThan(String value) {
            addCriterion("picture_path <", value, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathLessThanOrEqualTo(String value) {
            addCriterion("picture_path <=", value, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathLike(String value) {
            addCriterion("picture_path like", value, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathNotLike(String value) {
            addCriterion("picture_path not like", value, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathIn(List<String> values) {
            addCriterion("picture_path in", values, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathNotIn(List<String> values) {
            addCriterion("picture_path not in", values, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathBetween(String value1, String value2) {
            addCriterion("picture_path between", value1, value2, "picture_path");
            return (Criteria) this;
        }

        public Criteria andPicture_pathNotBetween(String value1, String value2) {
            addCriterion("picture_path not between", value1, value2, "picture_path");
            return (Criteria) this;
        }

        public Criteria andDesIsNull() {
            addCriterion("des is null");
            return (Criteria) this;
        }

        public Criteria andDesIsNotNull() {
            addCriterion("des is not null");
            return (Criteria) this;
        }

        public Criteria andDesEqualTo(String value) {
            addCriterion("des =", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotEqualTo(String value) {
            addCriterion("des <>", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThan(String value) {
            addCriterion("des >", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThanOrEqualTo(String value) {
            addCriterion("des >=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThan(String value) {
            addCriterion("des <", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThanOrEqualTo(String value) {
            addCriterion("des <=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLike(String value) {
            addCriterion("des like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotLike(String value) {
            addCriterion("des not like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesIn(List<String> values) {
            addCriterion("des in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotIn(List<String> values) {
            addCriterion("des not in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesBetween(String value1, String value2) {
            addCriterion("des between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotBetween(String value1, String value2) {
            addCriterion("des not between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andSIsNull() {
            addCriterion("s is null");
            return (Criteria) this;
        }

        public Criteria andSIsNotNull() {
            addCriterion("s is not null");
            return (Criteria) this;
        }

        public Criteria andSEqualTo(Integer value) {
            addCriterion("s =", value, "s");
            return (Criteria) this;
        }

        public Criteria andSNotEqualTo(Integer value) {
            addCriterion("s <>", value, "s");
            return (Criteria) this;
        }

        public Criteria andSGreaterThan(Integer value) {
            addCriterion("s >", value, "s");
            return (Criteria) this;
        }

        public Criteria andSGreaterThanOrEqualTo(Integer value) {
            addCriterion("s >=", value, "s");
            return (Criteria) this;
        }

        public Criteria andSLessThan(Integer value) {
            addCriterion("s <", value, "s");
            return (Criteria) this;
        }

        public Criteria andSLessThanOrEqualTo(Integer value) {
            addCriterion("s <=", value, "s");
            return (Criteria) this;
        }

        public Criteria andSIn(List<Integer> values) {
            addCriterion("s in", values, "s");
            return (Criteria) this;
        }

        public Criteria andSNotIn(List<Integer> values) {
            addCriterion("s not in", values, "s");
            return (Criteria) this;
        }

        public Criteria andSBetween(Integer value1, Integer value2) {
            addCriterion("s between", value1, value2, "s");
            return (Criteria) this;
        }

        public Criteria andSNotBetween(Integer value1, Integer value2) {
            addCriterion("s not between", value1, value2, "s");
            return (Criteria) this;
        }

        public Criteria andMIsNull() {
            addCriterion("m is null");
            return (Criteria) this;
        }

        public Criteria andMIsNotNull() {
            addCriterion("m is not null");
            return (Criteria) this;
        }

        public Criteria andMEqualTo(Integer value) {
            addCriterion("m =", value, "m");
            return (Criteria) this;
        }

        public Criteria andMNotEqualTo(Integer value) {
            addCriterion("m <>", value, "m");
            return (Criteria) this;
        }

        public Criteria andMGreaterThan(Integer value) {
            addCriterion("m >", value, "m");
            return (Criteria) this;
        }

        public Criteria andMGreaterThanOrEqualTo(Integer value) {
            addCriterion("m >=", value, "m");
            return (Criteria) this;
        }

        public Criteria andMLessThan(Integer value) {
            addCriterion("m <", value, "m");
            return (Criteria) this;
        }

        public Criteria andMLessThanOrEqualTo(Integer value) {
            addCriterion("m <=", value, "m");
            return (Criteria) this;
        }

        public Criteria andMIn(List<Integer> values) {
            addCriterion("m in", values, "m");
            return (Criteria) this;
        }

        public Criteria andMNotIn(List<Integer> values) {
            addCriterion("m not in", values, "m");
            return (Criteria) this;
        }

        public Criteria andMBetween(Integer value1, Integer value2) {
            addCriterion("m between", value1, value2, "m");
            return (Criteria) this;
        }

        public Criteria andMNotBetween(Integer value1, Integer value2) {
            addCriterion("m not between", value1, value2, "m");
            return (Criteria) this;
        }

        public Criteria andLIsNull() {
            addCriterion("l is null");
            return (Criteria) this;
        }

        public Criteria andLIsNotNull() {
            addCriterion("l is not null");
            return (Criteria) this;
        }

        public Criteria andLEqualTo(Integer value) {
            addCriterion("l =", value, "l");
            return (Criteria) this;
        }

        public Criteria andLNotEqualTo(Integer value) {
            addCriterion("l <>", value, "l");
            return (Criteria) this;
        }

        public Criteria andLGreaterThan(Integer value) {
            addCriterion("l >", value, "l");
            return (Criteria) this;
        }

        public Criteria andLGreaterThanOrEqualTo(Integer value) {
            addCriterion("l >=", value, "l");
            return (Criteria) this;
        }

        public Criteria andLLessThan(Integer value) {
            addCriterion("l <", value, "l");
            return (Criteria) this;
        }

        public Criteria andLLessThanOrEqualTo(Integer value) {
            addCriterion("l <=", value, "l");
            return (Criteria) this;
        }

        public Criteria andLIn(List<Integer> values) {
            addCriterion("l in", values, "l");
            return (Criteria) this;
        }

        public Criteria andLNotIn(List<Integer> values) {
            addCriterion("l not in", values, "l");
            return (Criteria) this;
        }

        public Criteria andLBetween(Integer value1, Integer value2) {
            addCriterion("l between", value1, value2, "l");
            return (Criteria) this;
        }

        public Criteria andLNotBetween(Integer value1, Integer value2) {
            addCriterion("l not between", value1, value2, "l");
            return (Criteria) this;
        }

        public Criteria andXlIsNull() {
            addCriterion("xl is null");
            return (Criteria) this;
        }

        public Criteria andXlIsNotNull() {
            addCriterion("xl is not null");
            return (Criteria) this;
        }

        public Criteria andXlEqualTo(Integer value) {
            addCriterion("xl =", value, "xl");
            return (Criteria) this;
        }

        public Criteria andXlNotEqualTo(Integer value) {
            addCriterion("xl <>", value, "xl");
            return (Criteria) this;
        }

        public Criteria andXlGreaterThan(Integer value) {
            addCriterion("xl >", value, "xl");
            return (Criteria) this;
        }

        public Criteria andXlGreaterThanOrEqualTo(Integer value) {
            addCriterion("xl >=", value, "xl");
            return (Criteria) this;
        }

        public Criteria andXlLessThan(Integer value) {
            addCriterion("xl <", value, "xl");
            return (Criteria) this;
        }

        public Criteria andXlLessThanOrEqualTo(Integer value) {
            addCriterion("xl <=", value, "xl");
            return (Criteria) this;
        }

        public Criteria andXlIn(List<Integer> values) {
            addCriterion("xl in", values, "xl");
            return (Criteria) this;
        }

        public Criteria andXlNotIn(List<Integer> values) {
            addCriterion("xl not in", values, "xl");
            return (Criteria) this;
        }

        public Criteria andXlBetween(Integer value1, Integer value2) {
            addCriterion("xl between", value1, value2, "xl");
            return (Criteria) this;
        }

        public Criteria andXlNotBetween(Integer value1, Integer value2) {
            addCriterion("xl not between", value1, value2, "xl");
            return (Criteria) this;
        }

        public Criteria andXl2IsNull() {
            addCriterion("xl2 is null");
            return (Criteria) this;
        }

        public Criteria andXl2IsNotNull() {
            addCriterion("xl2 is not null");
            return (Criteria) this;
        }

        public Criteria andXl2EqualTo(Integer value) {
            addCriterion("xl2 =", value, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl2NotEqualTo(Integer value) {
            addCriterion("xl2 <>", value, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl2GreaterThan(Integer value) {
            addCriterion("xl2 >", value, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl2GreaterThanOrEqualTo(Integer value) {
            addCriterion("xl2 >=", value, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl2LessThan(Integer value) {
            addCriterion("xl2 <", value, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl2LessThanOrEqualTo(Integer value) {
            addCriterion("xl2 <=", value, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl2In(List<Integer> values) {
            addCriterion("xl2 in", values, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl2NotIn(List<Integer> values) {
            addCriterion("xl2 not in", values, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl2Between(Integer value1, Integer value2) {
            addCriterion("xl2 between", value1, value2, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl2NotBetween(Integer value1, Integer value2) {
            addCriterion("xl2 not between", value1, value2, "xl2");
            return (Criteria) this;
        }

        public Criteria andXl3IsNull() {
            addCriterion("xl3 is null");
            return (Criteria) this;
        }

        public Criteria andXl3IsNotNull() {
            addCriterion("xl3 is not null");
            return (Criteria) this;
        }

        public Criteria andXl3EqualTo(Integer value) {
            addCriterion("xl3 =", value, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl3NotEqualTo(Integer value) {
            addCriterion("xl3 <>", value, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl3GreaterThan(Integer value) {
            addCriterion("xl3 >", value, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl3GreaterThanOrEqualTo(Integer value) {
            addCriterion("xl3 >=", value, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl3LessThan(Integer value) {
            addCriterion("xl3 <", value, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl3LessThanOrEqualTo(Integer value) {
            addCriterion("xl3 <=", value, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl3In(List<Integer> values) {
            addCriterion("xl3 in", values, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl3NotIn(List<Integer> values) {
            addCriterion("xl3 not in", values, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl3Between(Integer value1, Integer value2) {
            addCriterion("xl3 between", value1, value2, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl3NotBetween(Integer value1, Integer value2) {
            addCriterion("xl3 not between", value1, value2, "xl3");
            return (Criteria) this;
        }

        public Criteria andXl4IsNull() {
            addCriterion("xl4 is null");
            return (Criteria) this;
        }

        public Criteria andXl4IsNotNull() {
            addCriterion("xl4 is not null");
            return (Criteria) this;
        }

        public Criteria andXl4EqualTo(Integer value) {
            addCriterion("xl4 =", value, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl4NotEqualTo(Integer value) {
            addCriterion("xl4 <>", value, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl4GreaterThan(Integer value) {
            addCriterion("xl4 >", value, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl4GreaterThanOrEqualTo(Integer value) {
            addCriterion("xl4 >=", value, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl4LessThan(Integer value) {
            addCriterion("xl4 <", value, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl4LessThanOrEqualTo(Integer value) {
            addCriterion("xl4 <=", value, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl4In(List<Integer> values) {
            addCriterion("xl4 in", values, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl4NotIn(List<Integer> values) {
            addCriterion("xl4 not in", values, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl4Between(Integer value1, Integer value2) {
            addCriterion("xl4 between", value1, value2, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl4NotBetween(Integer value1, Integer value2) {
            addCriterion("xl4 not between", value1, value2, "xl4");
            return (Criteria) this;
        }

        public Criteria andXl5IsNull() {
            addCriterion("xl5 is null");
            return (Criteria) this;
        }

        public Criteria andXl5IsNotNull() {
            addCriterion("xl5 is not null");
            return (Criteria) this;
        }

        public Criteria andXl5EqualTo(Integer value) {
            addCriterion("xl5 =", value, "xl5");
            return (Criteria) this;
        }

        public Criteria andXl5NotEqualTo(Integer value) {
            addCriterion("xl5 <>", value, "xl5");
            return (Criteria) this;
        }

        public Criteria andXl5GreaterThan(Integer value) {
            addCriterion("xl5 >", value, "xl5");
            return (Criteria) this;
        }

        public Criteria andXl5GreaterThanOrEqualTo(Integer value) {
            addCriterion("xl5 >=", value, "xl5");
            return (Criteria) this;
        }

        public Criteria andXl5LessThan(Integer value) {
            addCriterion("xl5 <", value, "xl5");
            return (Criteria) this;
        }

        public Criteria andXl5LessThanOrEqualTo(Integer value) {
            addCriterion("xl5 <=", value, "xl5");
            return (Criteria) this;
        }

        public Criteria andXl5In(List<Integer> values) {
            addCriterion("xl5 in", values, "xl5");
            return (Criteria) this;
        }

        public Criteria andXl5NotIn(List<Integer> values) {
            addCriterion("xl5 not in", values, "xl5");
            return (Criteria) this;
        }

        public Criteria andXl5Between(Integer value1, Integer value2) {
            addCriterion("xl5 between", value1, value2, "xl5");
            return (Criteria) this;
        }

        public Criteria andXl5NotBetween(Integer value1, Integer value2) {
            addCriterion("xl5 not between", value1, value2, "xl5");
            return (Criteria) this;
        }

        public Criteria andOthersIsNull() {
            addCriterion("others is null");
            return (Criteria) this;
        }

        public Criteria andOthersIsNotNull() {
            addCriterion("others is not null");
            return (Criteria) this;
        }

        public Criteria andOthersEqualTo(Integer value) {
            addCriterion("others =", value, "others");
            return (Criteria) this;
        }

        public Criteria andOthersNotEqualTo(Integer value) {
            addCriterion("others <>", value, "others");
            return (Criteria) this;
        }

        public Criteria andOthersGreaterThan(Integer value) {
            addCriterion("others >", value, "others");
            return (Criteria) this;
        }

        public Criteria andOthersGreaterThanOrEqualTo(Integer value) {
            addCriterion("others >=", value, "others");
            return (Criteria) this;
        }

        public Criteria andOthersLessThan(Integer value) {
            addCriterion("others <", value, "others");
            return (Criteria) this;
        }

        public Criteria andOthersLessThanOrEqualTo(Integer value) {
            addCriterion("others <=", value, "others");
            return (Criteria) this;
        }

        public Criteria andOthersIn(List<Integer> values) {
            addCriterion("others in", values, "others");
            return (Criteria) this;
        }

        public Criteria andOthersNotIn(List<Integer> values) {
            addCriterion("others not in", values, "others");
            return (Criteria) this;
        }

        public Criteria andOthersBetween(Integer value1, Integer value2) {
            addCriterion("others between", value1, value2, "others");
            return (Criteria) this;
        }

        public Criteria andOthersNotBetween(Integer value1, Integer value2) {
            addCriterion("others not between", value1, value2, "others");
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