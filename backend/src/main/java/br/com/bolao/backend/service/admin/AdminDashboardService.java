package br.com.bolao.backend.service.admin;

import br.com.bolao.backend.dto.admin.DashboardResumoDTO;
import br.com.bolao.backend.dto.admin.RankingLinhaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDashboardService {

    private final AdminRankingService adminRankingService;
    private final AdminPartidaService adminPartidaService;

    public AdminDashboardService(AdminRankingService adminRankingService, AdminPartidaService adminPartidaService) {
        this.adminRankingService = adminRankingService;
        this.adminPartidaService = adminPartidaService;
    }

    public DashboardResumoDTO buscarResumo() {
        return new DashboardResumoDTO(
                1248,
                15672,
                adminPartidaService.contarPartidasPendentes(),
                64
        );
    }

    public List<RankingLinhaDTO> listarTopRanking() {
        return adminRankingService.listarRanking()
                .stream()
                .limit(5)
                .toList();
    }
}