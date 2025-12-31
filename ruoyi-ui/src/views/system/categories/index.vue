<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入分类名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类URL标识" prop="slug">
        <el-input
          v-model="queryParams.slug"
          placeholder="请输入分类URL标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父分类ID" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入父分类ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序顺序" prop="sortOrder">
        <el-input
          v-model="queryParams.sortOrder"
          placeholder="请输入排序顺序"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否激活" prop="isActive">
        <el-input
          v-model="queryParams.isActive"
          placeholder="请输入是否激活"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类图标" prop="icon">
        <el-input
          v-model="queryParams.icon"
          placeholder="请输入分类图标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类颜色" prop="color">
        <el-input
          v-model="queryParams.color"
          placeholder="请输入分类颜色"
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
          v-hasPermi="['system:categories:add']"
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
          v-hasPermi="['system:categories:edit']"
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
          v-hasPermi="['system:categories:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:categories:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="categoriesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="分类ID" align="center" prop="id" />
      <el-table-column label="分类名称" align="center" prop="name" />
      <el-table-column label="分类URL标识" align="center" prop="slug" />
      <el-table-column label="分类描述" align="center" prop="description" />
      <el-table-column label="父分类ID" align="center" prop="parentId" />
      <el-table-column label="排序顺序" align="center" prop="sortOrder" />
      <el-table-column label="是否激活" align="center" prop="isActive" />
      <el-table-column label="分类图标" align="center" prop="icon" />
      <el-table-column label="分类颜色" align="center" prop="color" />
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
            v-hasPermi="['system:categories:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:categories:remove']"
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

    <!-- 添加或修改反馈分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="分类URL标识" prop="slug">
          <el-input v-model="form.slug" placeholder="请输入分类URL标识" />
        </el-form-item>
        <el-form-item label="分类描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="父分类ID" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入父分类ID" />
        </el-form-item>
        <el-form-item label="排序顺序" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入排序顺序" />
        </el-form-item>
        <el-form-item label="是否激活" prop="isActive">
          <el-input v-model="form.isActive" placeholder="请输入是否激活" />
        </el-form-item>
        <el-form-item label="分类图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入分类图标" />
        </el-form-item>
        <el-form-item label="分类颜色" prop="color">
          <el-input v-model="form.color" placeholder="请输入分类颜色" />
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
import { listCategories, getCategories, delCategories, addCategories, updateCategories } from "@/api/system/categories"

export default {
  name: "Categories",
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
      // 反馈分类表格数据
      categoriesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        slug: null,
        description: null,
        parentId: null,
        sortOrder: null,
        isActive: null,
        icon: null,
        color: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "分类名称不能为空", trigger: "blur" }
        ],
        slug: [
          { required: true, message: "分类URL标识不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询反馈分类列表 */
    getList() {
      this.loading = true
      listCategories(this.queryParams).then(response => {
        this.categoriesList = response.rows
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
        name: null,
        slug: null,
        description: null,
        parentId: null,
        sortOrder: null,
        isActive: null,
        icon: null,
        color: null,
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
      this.title = "添加反馈分类"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getCategories(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改反馈分类"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCategories(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCategories(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除反馈分类编号为"' + ids + '"的数据项？').then(function() {
        return delCategories(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/categories/export', {
        ...this.queryParams
      }, `categories_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
