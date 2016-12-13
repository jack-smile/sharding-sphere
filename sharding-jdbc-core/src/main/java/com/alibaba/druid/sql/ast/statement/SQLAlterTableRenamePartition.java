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

import com.alibaba.druid.sql.ast.SQLObjectImpl;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SQLAlterTableRenamePartition extends SQLObjectImpl implements SQLAlterTableItem {
    
    private final List<SQLAssignItem> partition = new ArrayList<>(4);
    
    private final List<SQLAssignItem> to        = new ArrayList<>(4);
    
    @Override
    protected void acceptInternal(final SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, partition);
            acceptChild(visitor, to);
        }
        visitor.endVisit(this);
    }
}
