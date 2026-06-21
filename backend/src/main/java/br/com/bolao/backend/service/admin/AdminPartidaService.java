package br.com.bolao.backend.service.admin;

import br.com.bolao.backend.dto.admin.PartidaAdminDTO;
import br.com.bolao.backend.dto.admin.PartidaResultadoDTO;
import br.com.bolao.backend.exception.AdminException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPartidaService {

    private final AdminMockDataService adminMockDataService;

    public AdminPartidaService(AdminMockDataService adminMockDataService) {
        this.adminMockDataService = adminMockDataService;
    }

    public List<PartidaAdminDTO> listarPartidas() {
        return adminMockDataService.listarPartidas();
    }

    public PartidaResultadoDTO buscarParaResultado(Long id) {
        PartidaAdminDTO partida = listarPartidas()
                .stream()
                .filter(item -> item.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new AdminException("Partida não encontrada."));

        return new PartidaResultadoDTO(
                partida.id(),
                partida.selecaoA(),
                partida.selecaoB(),
                partida.fase(),
                partida.dataHora()
        );
    }

    public String lancarResultado(Long id, String golsA, String golsB) {
        int golsSelecaoA = converterGols(golsA);
        int golsSelecaoB = converterGols(golsB);

        validarResultado(golsSelecaoA, golsSelecaoB);

        PartidaResultadoDTO partida = buscarParaResultado(id);

        return partida.selecaoA() + " " + golsSelecaoA + " x " + golsSelecaoB + " " + partida.selecaoB();
    }

    public int contarPartidasPendentes() {
        return (int) listarPartidas()
                .stream()
                .filter(partida -> partida.status().equals("Pendente"))
                .count();
    }

    private int converterGols(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new AdminException("Informe os gols das duas seleções.");
        }

        if (!valor.matches("\\d+")) {
            throw new AdminException("Os gols devem ser números inteiros positivos.");
        }

        if (valor.length() > 2) {
            throw new AdminException("Informe um placar válido. O limite é de 20 gols por seleção.");
        }

        return Integer.parseInt(valor);
    }

    private void validarResultado(int golsA, int golsB) {
        if (golsA < 0 || golsB < 0) {
            throw new AdminException("Os gols não podem ser negativos.");
        }

        if (golsA > 20 || golsB > 20) {
            throw new AdminException("Informe um placar válido. O limite é de 20 gols por seleção.");
        }
    }
}