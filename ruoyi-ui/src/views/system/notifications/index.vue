<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="接收用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入接收用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通知标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入通知标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联ID" prop="referenceId">
        <el-input
          v-model="queryParams.referenceId"
          placeholder="请输入关联ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否已读" prop="isRead">
        <el-input
          v-model="queryParams.isRead"
          placeholder="请输入是否已读"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮件是否已发送" prop="isEmailSent">
        <el-input
          v-model="queryParams.isEmailSent"
          placeholder="请输入邮件是否已发送"
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
      <el-form-item label="阅读时间" prop="readAt">
        <el-date-picker clearable
          v-model="queryParams.readAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择阅读时间">
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
          v-hasPermi="['system:notifications:add']"
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
          v-hasPermi="['system:notifications:edit']"
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
          v-hasPermi="['system:notifications:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:notifications:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="notificationsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="通知ID" align="center" prop="id" />
      <el-table-column label="接收用户ID" align="center" prop="userId" />
      <el-table-column label="通知类型" align="center" prop="type" />
      <el-table-column label="通知标题" align="center" prop="title" />
      <el-table-column label="通知内容" align="center" prop="content" />
      <el-table-column label="关联类型" align="center" prop="referenceType" />
      <el-table-column label="关联ID" align="center" prop="referenceId" />
      <el-table-column label="是否已读" align="center" prop="isRead" />
      <el-table-column label="邮件是否已发送" align="center" prop="isEmailSent" />
      <el-table-column label="附加数据" align="center" prop="metadata" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="阅读时间" align="center" prop="readAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.readAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:notifications:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:notifications:remove']"
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

    <!-- 添加或修改通知对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="接收用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入接收用户ID" />
        </el-form-item>
        <el-form-item label="通知标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入通知标题" />
        </el-form-item>
        <el-form-item label="通知内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="关联ID" prop="referenceId">
          <el-input v-model="form.referenceId" placeholder="请输入关联ID" />
        </el-form-item>
        <el-form-item label="是否已读" prop="isRead">
          <el-input v-model="form.isRead" placeholder="请输入是否已读" />
        </el-form-item>
        <el-form-item label="邮件是否已发送" prop="isEmailSent">
          <el-input v-model="form.isEmailSent" placeholder="请输入邮件是否已发送" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdAt">
          <el-date-picker clearable
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="阅读时间" prop="readAt">
          <el-date-picker clearable
            v-model="form.readAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择阅读时间">
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
import { listNotifications, getNotifications, delNotifications, addNotifications, updateNotifications } from "@/api/system/notifications"

export default {
  name: "Notifications",
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
      // 通知表格数据
      notificationsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        type: null,
        title: null,
        content: null,
        referenceType: null,
        referenceId: null,
        isRead: null,
        isEmailSent: null,
        metadata: null,
        createdAt: null,
        readAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "接收用户ID不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "通知类型不能为空", trigger: "change" }
        ],
        title: [
          { required: true, message: "通知标题不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "通知内容不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询通知列表 */
    getList() {
      this.loading = true
      listNotifications(this.queryParams).then(response => {
        this.notificationsList = response.rows
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
        userId: null,
        type: null,
        title: null,
        content: null,
        referenceType: null,
        referenceId: null,
        isRead: null,
        isEmailSent: null,
        metadata: null,
        createdAt: null,
        readAt: null
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
      this.title = "添加通知"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getNotifications(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改通知"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateNotifications(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addNotifications(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除通知编号为"' + ids + '"的数据项？').then(function() {
        return delNotifications(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/notifications/export', {
        ...this.queryParams
      }, `notifications_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
