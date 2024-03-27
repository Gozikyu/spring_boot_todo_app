import React, { useState } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Task } from '@/features/tasks/types';
import { useUpdateTask } from '@/features/tasks/api/updateTask';
import { MenuItem } from '@mui/material';

//TODO: 一箇所で定義する
const status = [
  {
    value: 'NOT_STARTED',
    label: 'NOT_STARTED',
  },
  {
    value: 'IN_PROGRESS',
    label: 'IN_PROGRESS',
  },
  {
    value: 'DONE',
    label: 'DONE',
  },
];

type Props = {
  task: Task;
};

export default function FormDialog(props: Props) {
  const [open, setOpen] = useState(false);
  const [task, setTask] = useState<Task>(() => props.task);

  const taskMutation = useUpdateTask();

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSubmit = () => {
    taskMutation.mutate({ data: task });
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setTask((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  return (
    <React.Fragment>
      <Button variant="outlined" onClick={handleClickOpen}>
        編集
      </Button>
      <Dialog
        open={open}
        onClose={handleClose}
        PaperProps={{
          component: 'form',
          onSubmit: (event: React.FormEvent<HTMLFormElement>) => {
            event.preventDefault();
            handleClose();
          },
        }}
      >
        <DialogTitle>タスクの編集</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            required
            margin="dense"
            name="title"
            label="タイトル"
            type="text"
            fullWidth
            variant="standard"
            value={task.title}
            onChange={handleInputChange}
          />
          <TextField
            autoFocus
            required
            margin="dense"
            name="description"
            label="説明"
            type="text"
            fullWidth
            variant="standard"
            value={task.description}
            onChange={handleInputChange}
          />
          <TextField
            autoFocus
            required
            margin="dense"
            name="status"
            type="text"
            fullWidth
            variant="standard"
            value={task.status}
            select
            label="ステータス"
            defaultValue={task.status}
            onChange={handleInputChange}
          >
            {status.map((option) => (
              <MenuItem key={option.value} value={option.value}>
                {option.label}
              </MenuItem>
            ))}
          </TextField>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => {
              handleClose();
              setTask(props.task); // キャンセルの場合は元の状態に戻しておく
            }}
          >
            キャンセル
          </Button>
          <Button onClick={handleSubmit} type="submit">
            保存
          </Button>
        </DialogActions>
      </Dialog>
    </React.Fragment>
  );
}
