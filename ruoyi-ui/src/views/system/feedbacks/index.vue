<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="提交用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入提交用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类ID" prop="categoryId">
        <el-input
          v-model="queryParams.categoryId"
          placeholder="请输入分类ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="反馈标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入反馈标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="反馈URL标识" prop="slug">
        <el-input
          v-model="queryParams.slug"
          placeholder="请输入反馈URL标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投票总数" prop="voteCount">
        <el-input
          v-model="queryParams.voteCount"
          placeholder="请输入投票总数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论总数" prop="commentCount">
        <el-input
          v-model="queryParams.commentCount"
          placeholder="请输入评论总数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="查看次数" prop="viewCount">
        <el-input
          v-model="queryParams.viewCount"
          placeholder="请输入查看次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否精选" prop="featured">
        <el-input
          v-model="queryParams.featured"
          placeholder="请输入是否精选"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否匿名" prop="anonymous">
        <el-input
          v-model="queryParams.anonymous"
          placeholder="请输入是否匿名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否允许评论" prop="allowComments">
        <el-input
          v-model="queryParams.allowComments"
          placeholder="请输入是否允许评论"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="附件数量" prop="attachmentsCount">
        <el-input
          v-model="queryParams.attachmentsCount"
          placeholder="请输入附件数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预计工作量" prop="estimatedEffort">
        <el-input
          v-model="queryParams.estimatedEffort"
          placeholder="请输入预计工作量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预计完成日期" prop="estimatedCompletionDate">
        <el-date-picker clearable
          v-model="queryParams.estimatedCompletionDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预计完成日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="实际完成日期" prop="actualCompletionDate">
        <el-date-picker clearable
          v-model="queryParams.actualCompletionDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择实际完成日期">
        </el-date-picker>
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
      <el-form-item label="关闭时间" prop="closedAt">
        <el-date-picker clearable
          v-model="queryParams.closedAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择关闭时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="关闭人ID" prop="closedBy">
        <el-input
          v-model="queryParams.closedBy"
          placeholder="请输入关闭人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:feedbacks:add']"
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
          v-hasPermi="['system:feedbacks:edit']"
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
          v-hasPermi="['system:feedbacks:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:feedbacks:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="feedbacksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="反馈ID" align="center" prop="id" />
      <el-table-column label="提交用户ID" align="center" prop="userId" />
      <el-table-column label="分类ID" align="center" prop="categoryId" />
      <el-table-column label="反馈标题" align="center" prop="title" />
      <el-table-column label="反馈URL标识" align="center" prop="slug" />
      <el-table-column label="反馈内容" align="center" prop="content" />
      <el-table-column label="内容摘要" align="center" prop="summary" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="优先级" align="center" prop="priority" />
      <el-table-column label="可见性" align="center" prop="visibility" />
      <el-table-column label="投票总数" align="center" prop="voteCount" />
      <el-table-column label="评论总数" align="center" prop="commentCount" />
      <el-table-column label="查看次数" align="center" prop="viewCount" />
      <el-table-column label="是否精选" align="center" prop="featured" />
      <el-table-column label="是否匿名" align="center" prop="anonymous" />
      <el-table-column label="是否允许评论" align="center" prop="allowComments" />
      <el-table-column label="标签列表" align="center" prop="tags" />
      <el-table-column label="附件数量" align="center" prop="attachmentsCount" />
      <el-table-column label="预计工作量" align="center" prop="estimatedEffort" />
      <el-table-column label="预计完成日期" align="center" prop="estimatedCompletionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.estimatedCompletionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际完成日期" align="center" prop="actualCompletionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.actualCompletionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
      <el-table-column label="关闭时间" align="center" prop="closedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.closedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="关闭人ID" align="center" prop="closedBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:feedbacks:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:feedbacks:remove']"
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

    <!-- 添加或修改反馈意见对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="提交用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入提交用户ID" />
        </el-form-item>
        <el-form-item label="分类ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入分类ID" />
        </el-form-item>
        <el-form-item label="反馈标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入反馈标题" />
        </el-form-item>
        <el-form-item label="反馈URL标识" prop="slug">
          <el-input v-model="form.slug" placeholder="请输入反馈URL标识" />
        </el-form-item>
        <el-form-item label="反馈内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="内容摘要" prop="summary">
          <el-input v-model="form.summary" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="投票总数" prop="voteCount">
          <el-input v-model="form.voteCount" placeholder="请输入投票总数" />
        </el-form-item>
        <el-form-item label="评论总数" prop="commentCount">
          <el-input v-model="form.commentCount" placeholder="请输入评论总数" />
        </el-form-item>
        <el-form-item label="查看次数" prop="viewCount">
          <el-input v-model="form.viewCount" placeholder="请输入查看次数" />
        </el-form-item>
        <el-form-item label="是否精选" prop="featured">
          <el-input v-model="form.featured" placeholder="请输入是否精选" />
        </el-form-item>
        <el-form-item label="是否匿名" prop="anonymous">
          <el-input v-model="form.anonymous" placeholder="请输入是否匿名" />
        </el-form-item>
        <el-form-item label="是否允许评论" prop="allowComments">
          <el-input v-model="form.allowComments" placeholder="请输入是否允许评论" />
        </el-form-item>
        <el-form-item label="附件数量" prop="attachmentsCount">
          <el-input v-model="form.attachmentsCount" placeholder="请输入附件数量" />
        </el-form-item>
        <el-form-item label="预计工作量" prop="estimatedEffort">
          <el-input v-model="form.estimatedEffort" placeholder="请输入预计工作量" />
        </el-form-item>
        <el-form-item label="预计完成日期" prop="estimatedCompletionDate">
          <el-date-picker clearable
            v-model="form.estimatedCompletionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预计完成日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际完成日期" prop="actualCompletionDate">
          <el-date-picker clearable
            v-model="form.actualCompletionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择实际完成日期">
          </el-date-picker>
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
        <el-form-item label="关闭时间" prop="closedAt">
          <el-date-picker clearable
            v-model="form.closedAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择关闭时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="关闭人ID" prop="closedBy">
          <el-input v-model="form.closedBy" placeholder="请输入关闭人ID" />
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
import { listFeedbacks, getFeedbacks, delFeedbacks, addFeedbacks, updateFeedbacks } from "@/api/system/feedbacks"

export default {
  name: "Feedbacks",
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
      // 反馈意见表格数据
      feedbacksList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        categoryId: null,
        title: null,
        slug: null,
        content: null,
        summary: null,
        status: null,
        priority: null,
        visibility: null,
        voteCount: null,
        commentCount: null,
        viewCount: null,
        featured: null,
        anonymous: null,
        allowComments: null,
        tags: null,
        attachmentsCount: null,
        estimatedEffort: null,
        estimatedCompletionDate: null,
        actualCompletionDate: null,
        createdAt: null,
        updatedAt: null,
        closedAt: null,
        closedBy: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "提交用户ID不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "分类ID不能为空", trigger: "blur" }
        ],
        title: [
          { required: true, message: "反馈标题不能为空", trigger: "blur" }
        ],
        slug: [
          { required: true, message: "反馈URL标识不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "反馈内容不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询反馈意见列表 */
    getList() {
      this.loading = true
      listFeedbacks(this.queryParams).then(response => {
        this.feedbacksList = response.rows
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
        categoryId: null,
        title: null,
        slug: null,
        content: null,
        summary: null,
        status: null,
        priority: null,
        visibility: null,
        voteCount: null,
        commentCount: null,
        viewCount: null,
        featured: null,
        anonymous: null,
        allowComments: null,
        tags: null,
        attachmentsCount: null,
        estimatedEffort: null,
        estimatedCompletionDate: null,
        actualCompletionDate: null,
        createdAt: null,
        updatedAt: null,
        closedAt: null,
        closedBy: null
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
      this.title = "添加反馈意见"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getFeedbacks(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改反馈意见"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFeedbacks(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addFeedbacks(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除反馈意见编号为"' + ids + '"的数据项？').then(function() {
        return delFeedbacks(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/feedbacks/export', {
        ...this.queryParams
      }, `feedbacks_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
