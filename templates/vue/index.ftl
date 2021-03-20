<template>
    <div class="${nameUtils.getKebabName(entity.name)}-index">
        <!-- 弹窗 -->
        <el-dialog width="50%"
                   :title="isAdd?'新增':'修改'"
                   :visible.sync="showAdd"
                   :append-to-body="true"
                   :modal-append-to-body="false"
                   :close-on-click-modal="false">
            <el-form ref="elForm"
                     :rules="formRules"
                     :model="formData">
                <#list entity.fields as field>
                <el-form-item label="${field.description}"
                              prop="${field.name}">
                    <#if field.type=='Date'>
                    <el-date-picker v-model="formData.${field.name}"
                                    type="datetime"
                                    placeholder="请选择${field.description}">
                    </el-date-picker>
                    <#elseif field.type=='Boolean'>
                    <el-checkbox v-model="formData.${field.name}">${field.description}</el-checkbox>
                    <#else>
                    <el-input v-model="formData.${field.name}"
                              placeholder="请输入${field.description}"></el-input>
                    </#if>
                </el-form-item>
                </#list>
            </el-form>
            <div slot="footer">
                <el-button size="mini"
                           @click="showAdd=false">取消</el-button>
                <el-button size="mini"
                           type="primary"
                           v-loading="isLoadingAdd"
                           @click="onConfirmClick">确定</el-button>
            </div>
        </el-dialog>

        <!-- 按钮 -->
        <el-button type="primary"
                   icon="el-icon-plus"
                   @click="onAddClick">新增</el-button>

        <!-- 列表 -->
        <el-table v-loading="isLoadingList"
                  :data="tableData">
            <#list entity.fields as field>
            <el-table-column label="${field.description}"
                             prop="${field.name}"
                             show-overflow-tooltip>
            </el-table-column>
            </#list>
            <!-- 操作 -->
            <el-table-column label="操作"
                             width="120">
                <template slot-scope="scope">
                    <el-button type="text"
                               icon="el-icon-edit"
                               @click="onEditClick(scope.row)"></el-button>
                    <el-button type="text"
                               icon="el-icon-delete"
                               @click="onDeleteClick(scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "${nameUtils.getKebabName(entity.name)}-index",
        data() {
            return {
                tableData: [],
                isLoadingList: false,
                isAdd: true,
                showAdd: false,
                isLoadingAdd: false,
                formData: {},
                formRules: {
                    <#list entity.fields as field>
                    <#if !field.allowNull && field.type != 'Boolean'>
                    ${field.name}: [
                        <#if field.type == 'Date'>
                        { required: true, message: '请选择${field.description}', trigger: 'blur' },
                        <#else>
                        { required: true, message: '请输入${field.description}', trigger: 'blur' },
                        </#if>
                    ],
                    </#if>
                    </#list>
                },
            };
        },
        mounted() {
            this.list();
        },
        methods: {
            /* 获取列表 */
            list() {
                this.isLoadingList = true;
                /* 从后端获取数据 */
                this.isLoadingList = false;
            },
            /* 新增 */
            onAddClick() {
                this.formData = {};
                this.isAdd = true;
                this.showAdd = true;
            },
            /* 修改 */
            onEditClick(val) {
                this.formData = val;
                this.isAdd = false;
                this.showAdd = true;
            },
            /* 删除 */
            onDeleteClick(val) {
                this.$confirm("确认删除？", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() => {
                        /* 删除数据 */
                        this.list();
                    })
                    .catch(() => {});
            },
            /* 保存 */
            onConfirmClick() {
                this.$refs.elForm.validate((valid) => {
                    if (valid) {
                        if (this.isAdd) {
                            this.isLoadingAdd = true;
                            /* 新增数据 */
                            this.isLoadingAdd = false;
                            this.showAdd = false;
                            this.list();
                        } else {
                            this.isLoadingAdd = true;
                            /* 修改数据 */
                            this.isLoadingAdd = false;
                            this.showAdd = false;
                            this.list();
                        }
                    }
                })
            },
        },
    };
</script>
<style scoped>
</style>
