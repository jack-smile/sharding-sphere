/*
 * Copyright 1999-2101 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.ast.statement;

import com.alibaba.druid.sql.ast.SQLDataType;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLObjectImpl;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SQLColumnDefinition extends SQLObjectImpl implements SQLTableElement {
    
    private SQLName name;
    
    private SQLDataType dataType;
    
    private SQLExpr defaultExpr;
    
    private SQLExpr comment;
    
    private Boolean enable;
    
    private final List<SQLColumnConstraint> constraints = new ArrayList<>();
    
    public void setDefaultExpr(final SQLExpr defaultExpr) {
        if (null != defaultExpr) {
            defaultExpr.setParent(this);
        }
        this.defaultExpr = defaultExpr;
    }
    
    @Override
    protected void acceptInternal(final SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChild(visitor, name);
            this.acceptChild(visitor, dataType);
            this.acceptChild(visitor, defaultExpr);
            this.acceptChild(visitor, constraints);
        }
        visitor.endVisit(this);
    }
    
    @Override
    public void output(final StringBuffer buffer) {
        name.output(buffer);
        buffer.append(' ');
        dataType.output(buffer);
        if (null != defaultExpr) {
            buffer.append(" DEFAULT ");
            defaultExpr.output(buffer);
        }
    }
}
