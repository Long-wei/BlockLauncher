<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="配置键" prop="settingKey">
        <el-input
          v-model="queryParams.settingKey"
          placeholder="请输入配置键"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="配置分类" prop="category">
        <el-input
          v-model="queryParams.category"
          placeholder="请输入配置分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="配置描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入配置描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否公开" prop="isPublic">
        <el-input
          v-model="queryParams.isPublic"
          placeholder="请输入是否公开"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdAt">
        <el-date-picker clearable
          v-model="queryParams.createdAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedAt">
        <el-date-picker clearable
          v-model="queryParams.updatedAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:Opinion_configuration:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:Opinion_configuration:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:Opinion_configuration:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:Opinion_configuration:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="Opinion_configurationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="配置ID" align="center" prop="id" />
      <el-table-column label="配置键" align="center" prop="settingKey" />
      <el-table-column label="配置值" align="center" prop="settingValue" />
      <el-table-column label="值类型" align="center" prop="settingType" />
      <el-table-column label="配置分类" align="center" prop="category" />
      <el-table-column label="配置描述" align="center" prop="description" />
      <el-table-column label="是否公开" align="center" prop="isPublic" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:Opinion_configuration:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:Opinion_configuration:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改系统配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="配置键" prop="settingKey">
          <el-input v-model="form.settingKey" placeholder="请输入配置键" />
        </el-form-item>
        <el-form-item label="配置值" prop="settingValue">
          <el-input v-model="form.settingValue" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="配置分类" prop="category">
          <el-input v-model="form.category" placeholder="请输入配置分类" />
        </el-form-item>
        <el-form-item label="配置描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入配置描述" />
        </el-form-item>
        <el-form-item label="是否公开" prop="isPublic">
          <el-input v-model="form.isPublic" placeholder="请输入是否公开" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdAt">
          <el-date-picker clearable
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedAt">
          <el-date-picker clearable
            v-model="form.updatedAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOpinion_configuration, getOpinion_configuration, delOpinion_configuration, addOpinion_configuration, updateOpinion_configuration } from "@/api/system/Opinion_configuration"

export default {
  name: "Opinion_configuration",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 系统配置表格数据
      Opinion_configurationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        settingKey: null,
        settingValue: null,
        settingType: null,
        category: null,
        description: null,
        isPublic: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        settingKey: [
          { required: true, message: "配置键不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询系统配置列表 */
    getList() {
      this.loading = true
      listOpinion_configuration(this.queryParams).then(response => {
        this.Opinion_configurationList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        settingKey: null,
        settingValue: null,
        settingType: null,
        category: null,
        description: null,
        isPublic: null,
        createdAt: null,
        updatedAt: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加系统配置"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getOpinion_configuration(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改系统配置"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOpinion_configuration(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addOpinion_configuration(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除系统配置编号为"' + ids + '"的数据项？').then(function() {
        return delOpinion_configuration(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/Opinion_configuration/export', {
        ...this.queryParams
      }, `Opinion_configuration_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
