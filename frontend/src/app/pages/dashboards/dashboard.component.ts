import { Component, inject } from '@angular/core';
import { TranslationService } from '../../core/services/translation.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
  protected t = inject(TranslationService);
  
  // Dados mockados para visualização
  stats = [
    { label: 'Total de Tarefas', value: '124', color: '#6366f1' },
    { label: 'Concluídas', value: '85', color: '#10b981' },
    { label: 'Em Progresso', value: '32', color: '#f59e0b' },
    { label: 'Atrasadas', value: '7', color: '#ef4444' }
  ];
}