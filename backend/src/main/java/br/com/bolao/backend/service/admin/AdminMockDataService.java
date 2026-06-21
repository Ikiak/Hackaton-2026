package br.com.bolao.backend.service.admin;

import br.com.bolao.backend.dto.admin.PartidaAdminDTO;
import br.com.bolao.backend.dto.admin.UsuarioRankingBaseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminMockDataService {

    public int buscarTotalUsuarios() {
        return 1248;
    }

    public int buscarTotalPalpites() {
        return 15672;
    }

    public int buscarUsuariosAtivos24h() {
        return 64;
    }

    public List<PartidaAdminDTO> listarPartidas() {
        return List.of(
                new PartidaAdminDTO(1L, "Brasil", "Argentina", "Final", "16/07/2026 16:00", "Pendente", "-"),
                new PartidaAdminDTO(2L, "França", "Alemanha", "Semifinal", "12/07/2026 15:00", "Pendente", "-"),
                new PartidaAdminDTO(3L, "Espanha", "Portugal", "Quartas", "08/07/2026 18:00", "Encerrada", "2 x 1"),
                new PartidaAdminDTO(4L, "Inglaterra", "Itália", "Oitavas", "04/07/2026 16:00", "Encerrada", "1 x 1"),
                new PartidaAdminDTO(5L, "Uruguai", "México", "Grupos", "25/06/2026 21:00", "Pendente", "-")
        );
    }

    public List<UsuarioRankingBaseDTO> listarUsuariosRanking() {
        return List.of(
                new UsuarioRankingBaseDTO("Ana Silva", 150, 8, LocalDate.of(2026, 2, 10)),
                new UsuarioRankingBaseDTO("João Pereira", 150, 6, LocalDate.of(2026, 2, 8)),
                new UsuarioRankingBaseDTO("Bruno Costa", 150, 8, LocalDate.of(2026, 2, 5)),
                new UsuarioRankingBaseDTO("Marcos Lima", 135, 7, LocalDate.of(2026, 2, 15)),
                new UsuarioRankingBaseDTO("Carla Souza", 120, 5, LocalDate.of(2026, 2, 20)),
                new UsuarioRankingBaseDTO("Pedro Alves", 110, 4, LocalDate.of(2026, 2, 23)),
                new UsuarioRankingBaseDTO("Lucas Santos", 95, 3, LocalDate.of(2026, 3, 1)),
                new UsuarioRankingBaseDTO("Fernanda Rocha", 90, 3, LocalDate.of(2026, 3, 3)),
                new UsuarioRankingBaseDTO("Rafael Nunes", 85, 2, LocalDate.of(2026, 3, 5)),
                new UsuarioRankingBaseDTO("Juliana Martins", 80, 2, LocalDate.of(2026, 3, 7)),
                new UsuarioRankingBaseDTO("Gustavo Mendes", 75, 2, LocalDate.of(2026, 3, 10)),
                new UsuarioRankingBaseDTO("Camila Ferreira", 70, 1, LocalDate.of(2026, 3, 12))
        );
    }
}